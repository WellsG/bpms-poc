package com.example.poc.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.bpmsremote.client2.BpmsClient;
import com.example.bpmsremote.client2.BpmsClientUtil;
import com.example.bpmsremote.model.ProcessInstanceLog;
import com.example.poc.model.ProcessInstance;
import com.google.gson.Gson;


@WebServlet("/instanceimage")
public class ProcessImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessImageServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String instanceId = request.getParameter("processInstanceId");
        BpmsClient bpmsClient = BpmsClientUtil.getBpmsClient();
        try {
            response.getWriter().write(bpmsClient.getProcessImage(Long.parseLong(instanceId)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
