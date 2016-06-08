package com.example.poc.web;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.bpmsremote.client2.BpmsClient;
import com.example.bpmsremote.client2.BpmsClientUtil;
import com.google.gson.Gson;


@WebServlet("/complete")
public class TaskServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServlet.class);
    private static final String USERID = "wguo";

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String taskId = request.getParameter("taskId");
        Map<String, String> params = new HashMap<String, String>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramKey = parameterNames.nextElement();
            String paramValue = request.getParameter(paramKey);
            params.put(paramKey, paramValue);
        }
        params.put("userId", USERID);
        BpmsClient bpmsClient = BpmsClientUtil.getBpmsClient();
        try {
            bpmsClient.startHumantask(Long.parseLong(taskId), params);
            bpmsClient.completeHumantask(Long.parseLong(taskId), params);
            response.getWriter().write("SUCCESS");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
