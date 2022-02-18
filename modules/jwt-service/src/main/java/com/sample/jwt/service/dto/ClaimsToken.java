package com.sample.jwt.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Ivan Sánchez
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClaimsToken implements Serializable {

    @JsonProperty("exp")
    private long expirationTime;
    @JsonProperty("iat")
    private String issuedAt;
    @JsonProperty("jti")
    private String jwtId;
    @JsonProperty("iss")
    private String issuer;
    @JsonProperty("sub")
    private String subject;
    @JsonProperty("typ")
    private String type;
    @JsonProperty("azp")
    private String authorizedParty;
    @JsonProperty("session_state")
    private String sessionState;
    @JsonProperty("acr")
    private String authenticationContext;
    @JsonProperty("scope")
    private String scope;
    @JsonProperty("sid")
    private String sessionId;
    @JsonProperty("given_name")
    private String firstName;
    @JsonProperty("family_name")
    private String lastName;
    @JsonProperty("email")
    private String emailAddress;
    @JsonProperty("username")
    private String userName;

}