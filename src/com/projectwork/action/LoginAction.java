package com.projectwork.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.iss.ketan.imp.WebImporInitalizer;
import com.opensymphony.xwork2.ActionSupport;
import com.projectwork.bean.FeederBean;
import com.projectwork.constants.TestProjectConstantsIfc;
import com.projectwork.impl.CompanyServiceImpl;
import com.projectwork.impl.LoginServiceImpl;

public class LoginAction extends ActionSupport implements TestProjectConstantsIfc, ServletRequestAware
{
    /**
     * 
     */
    private static final long serialVersionUID = 8402900469763599313L;

    private String userName;

    private String password;

    private String feederName;

    private List<FeederBean> feederList = new ArrayList<FeederBean>();

    private List<String> feederListSearch = new ArrayList<String>();

    private HttpServletRequest request;

    /**
     * This method will validate user credentials and will navigate to main
     * screen if validation is successful.
     * 
     * @param
     * @return success: Successful validation
     * @return error: Invalid User credentials
     * @throws Exception
     */

    public String execute() throws Exception
    {
        LoginServiceImpl loginObject = new LoginServiceImpl();

        if (userName.equals("admin"))
        {
            if (password.equals("admin"))
            {
                request.getSession().setAttribute(USER_TYPE, ADMINISTRATOR);
                request.getSession().setAttribute(USER_STATUS, LOGGED_IN);
            }
            else
            {
                addActionError(getText("loginError.message"));
                return RETURN_ERROR;
            }
        }

        else
        {
            boolean isUserPresent = loginObject.validateLoginCredentials(userName, password);

            if (!isUserPresent)
            {
                // If user is not present in the database then error message
                // will be
                // displayed on login screen.

                addActionError(getText("loginError.message"));
                return RETURN_ERROR;
            }
            else
            {

                // Check if user attempting to login is redirected from forgot
                // password flow.

                boolean isPasswordResetRequired = loginObject.isPasswordResetFlagPresent(userName);
                if (isPasswordResetRequired)
                {
                    return "resetPassword";
                }

                request.getSession().setAttribute(USER_STATUS, LOGGED_IN);
            }
        }

        request.getSession().setAttribute(USER_NAME, userName);

        // Check if any old folder exists holding temporary images

        WebImporInitalizer obj = new WebImporInitalizer();
        obj.main(null);

        // Getting current system time for creating unique file name

        java.util.Date date = new java.util.Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long currentTime = calendar.getTimeInMillis();
        String currentTimeStamp = Long.toString(currentTime);

        // Path to temporary system directory to store temporary images

        String contextPath = request.getRealPath(File.separator);

        // Check if any old folder exists holding temporary images

        deleteOldImageDirectory(contextPath);

        String imageFileDirPath = contextPath + userName + "_tempProjectImages" + currentTimeStamp;

        request.getSession().setAttribute("imageFileDirPath", imageFileDirPath);

        File imageFileDir = new File(imageFileDirPath);

        // Create a sub directory inside system directory for storing header
        // images

        if (!(imageFileDir.exists()))
        {
            imageFileDir.mkdir();
        }

        String headerImageName = "headerImage" + currentTimeStamp + ".png";
        String headerImagePath = imageFileDirPath + File.separator + headerImageName;

        // Get company ID for logged in user

        int companyID = loginObject.getCompanyID(userName);
        request.getSession().setAttribute("CompanyID", companyID);

        CompanyServiceImpl companyServiceObj = new CompanyServiceImpl();

        // Get company Logo based on logged in user

        boolean isLogoCreated = companyServiceObj.getCompanyLogo(companyID, headerImagePath);

        // Save path to header file in session to display header image.

        String pathForJsp = userName + "_tempProjectImages_" + currentTimeStamp + File.separator + headerImageName;

        request.getSession().setAttribute("currentTimeStamp", currentTimeStamp);
        request.getSession().setAttribute("headerImagePath", pathForJsp);

        // Get feeder information based on company ID

        feederList = new LoginServiceImpl().getFeederData(companyID);

        Collections.sort(feederList, new Comparator<FeederBean>()
        {
            public int compare(FeederBean f1, FeederBean f2)
            {
                return f1.getFeeder().compareTo(f2.getFeeder());
            }
        });

        // Get feeder list to be displayed in drop down

        Iterator<FeederBean> iterator = feederList.iterator();

        while (iterator.hasNext())
        {
            FeederBean bean = iterator.next();
            feederListSearch.add(bean.getFeeder());
        }

        request.getSession().setAttribute("feederList", feederList);

        return RETURN_SUCCESS;
    }

    private void deleteOldImageDirectory(String contextPath)
    {
        File dir = new File(contextPath);
        for (File file : dir.listFiles())
        {
            if (file.isDirectory())
            {
                if (file.getName().contains("_tempProjectImages_"))
                {
                    System.out.println(file.getName());
                    String oldFileName = file.getName();

                    int indexTimeString = oldFileName.lastIndexOf("_");
                    final String key = oldFileName.substring(0, indexTimeString);
                    new LogoutAction().deleteDir(file);
                }
            }
        }

    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setServletRequest(HttpServletRequest httpServletRequest)
    {
        this.request = httpServletRequest;
    }

    public List<FeederBean> getFeederList()
    {
        return feederList;
    }

    public void setFeederList(List<FeederBean> feederList)
    {
        this.feederList = feederList;
    }

    public List<String> getFeederListSearch()
    {
        return feederListSearch;
    }

    public void setFeederListSearch(List<String> feederListSearch)
    {
        this.feederListSearch = feederListSearch;
    }

    public String getFeederName()
    {
        return feederName;
    }

    public void setFeederName(String feederName)
    {
        this.feederName = feederName;
    }

}