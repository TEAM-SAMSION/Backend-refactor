package com.pawith.log.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestInfoFormat {
    private final String threadId;
    private final String url;
    private final String method;
    private final String ip;
    private final String authorizationHeader;
}
