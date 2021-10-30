/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import user.User;

/**
 *
 * @author letie
 */
public class AuthenFilter implements Filter {

    private static final boolean debug = true;

    private FilterConfig filterConfig = null;
    private final List<String> customerResources;
    private final List<String> adminResources;

    public AuthenFilter() {
        customerResources = new ArrayList<>();
        customerResources.add("");
        customerResources.add("login.jsp");
        customerResources.add("LoginController");
        customerResources.add("shopping.jsp");
        customerResources.add("ViewAllProductController");
        customerResources.add("view-cart.jsp");
        customerResources.add("check-out.jsp");

        adminResources = new ArrayList<>();
        adminResources.add("");
        adminResources.add("login.jsp");
        adminResources.add("LoginController");
        adminResources.add("admin.jsp");
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFilter:DoBeforeProcessing");
        }
    }

    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (debug) {
            log("AuthenFilter:DoAfterProcessing");
        }
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        if (uri.contains("login.jsp") || uri.contains("sign-up.jsp")
                || uri.contains("MainController")) {
            chain.doFilter(request, response);
        } else {
            String resource = getResourceString(uri);
            HttpSession session = req.getSession();

            if (session == null || session.getAttribute("CURRENT_USER") == null) {
                res.sendRedirect("login.jsp");
            } else {
                User loginUser = (User) session.getAttribute("CURRENT_USER");
                String roleID = loginUser.getRoleId();
                if ("AD".equals(roleID) && adminResources.contains(resource)) {
                    chain.doFilter(request, response);
                } else if ("CU".equals(roleID) && customerResources.contains(resource)) {
                    chain.doFilter(request, response);
                } else {
                    res.sendRedirect("login.jsp");
                }
            }
        }
    }

    private String getResourceString(String url) {
        int index = url.lastIndexOf("/");
        return url.substring(index + 1);
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
    }

    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("AuthenFilter:Initializing filter");
            }
        }
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("AuthenFilter()");
        }
        StringBuffer sb = new StringBuffer("AuthenFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
