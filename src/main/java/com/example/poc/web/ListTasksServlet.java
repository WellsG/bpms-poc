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

import com.example.poc.model.Task;
import com.google.gson.Gson;


@WebServlet("/tasks")
public class ListTasksServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ListTasksServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        List<Task> tasks = new ArrayList<Task>();
        Task t1 = new Task();
        t1.setId(1l);
        t1.setTaskName("PM Review");
        tasks.add(t1);
        Task t2 = new Task();
        t2.setId(2l);
        t2.setTaskName("PM Review");
        tasks.add(t2);

        response.getWriter().write(new Gson().toJson(tasks));

    }
}
