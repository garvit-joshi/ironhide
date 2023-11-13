package org.garvit.IronHide.models;

import org.springframework.http.HttpStatus;

/**
 * @author garvit-joshi on 16/07/23
 */
public record APIInfo<T>(HttpStatus status, T message) {}

