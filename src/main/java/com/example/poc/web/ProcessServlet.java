package com.example.poc.web;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/process")
public class ProcessServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String repoName = request.getParameter("repoName");
        String ldapGroup = request.getParameter("ldapGroup");
        String groupName = request.getParameter("groupName");
        String repoDesc = request.getParameter("repoDesc");

        LOGGER.info("repoName: {}", repoName);
        LOGGER.info("ldapGroup: {}", ldapGroup);
        LOGGER.info("groupName: {}", groupName);
        LOGGER.info("repoDesc: {}", repoDesc);

    }
}
