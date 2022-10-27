package com.example.projectpractice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogServive {

  public void log(){
    log.error("error!!!!");
    log.warn("warn!!!!");
    log.info("info!!!!");
    log.debug("debug!!!!");
    log.trace("trace!!!!");
  }
}
