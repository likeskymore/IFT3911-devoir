package com.example.demo.users.jobs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;

import com.example.demo.users.jobs.handlers.SendWelcomeEmailJobHandler;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SendWelcomeEmailJob implements JobRequest {

  private Long userId;

  @Override
  public Class<? extends JobRequestHandler> getJobRequestHandler() {
    return SendWelcomeEmailJobHandler.class;
  }
}
