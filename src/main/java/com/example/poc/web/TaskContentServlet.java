package com.example.poc.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.bpmsremote.client2.BpmsClient;
import com.example.bpmsremote.client2.BpmsClientUtil;
import com.example.bpmsremote.model.TaskContent;
import com.example.poc.model.Task;
import com.google.gson.Gson;


@WebServlet("/task")
public class TaskContentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskContentServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        BpmsClient bpmsClient = BpmsClientUtil.setUp();
        String taskId = request.getParameter("taskId");
        try {
            TaskContent content = bpmsClient.getTaskContent(Long.parseLong(taskId));
            response.getWriter().write(new Gson().toJson(content.getContentMap()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
