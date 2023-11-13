package org.garvit.IronHide.controllers;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BaseController {

  @Value("${spring.application.name}")
  private String appName;

  @Value("${git.commit.id.full}")
  private String commitId;

  @Value("${git.branch}")
  private String branch;

  @GetMapping
  public ResponseEntity<String> homePage() {
    val message =
        String.format(
            "<h4>Hello World!</h4> <pre word-wrap: break-word; white-space: pre-wrap;>This is %s. <br>Branch: %s <br>Commit: %s<pre>",
            appName, branch, commitId);
    return ResponseEntity.ok(message);
  }
}
