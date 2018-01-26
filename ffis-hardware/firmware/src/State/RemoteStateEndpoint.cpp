//
// Created by torben on 24.04.16.
//

#include <HardwareSerial.h>
#include <WiFiClientSecure.h>
#include <ESP8266HTTPClient.h>

#include "RemoteStateEndpoint.h"
#include "ArduinoJson.h"

#include "defines.h"


RemoteStateEndpoint::RemoteStateEndpoint(String url) {
    this->host = "state.haec.de";
    this->url = url;
}


bool RemoteStateEndpoint::setState(bool state) {
    String  callUrl = "/" + this->url + "/set?state=" + boolToString(state);
    String result = sendGetRequest(callUrl);

    bool newState = extractStateFromJson(result);
    return newState;
}

bool RemoteStateEndpoint::getState() {
    bool result = extractStateFromJson(sendGetRequest("/" + this->url + "/get"));
    if(result)
        Serial.println("ns 1");
    else
        Serial.println("ns 0");

    return result;
}

String RemoteStateEndpoint::sendGetRequest(String url) {


    String fullUrl = "http://" +host+ url;

    Serial.println("Calling " + fullUrl);

    HTTPClient client;
    client.begin(fullUrl);
    client.setAuthorization(HTTP_BASIC_AUTH);
    client.GET();
    String result= client.getString();
    Serial.println("\tResult: " + result);

    return result;
}


bool RemoteStateEndpoint::extractStateFromJson(String json) {
    Serial.println("\t\t json: "+ json);
    StaticJsonBuffer<500> jsonBuffer;
    JsonObject &root = jsonBuffer.parseObject(json);
    
    String result = root["state"][0];

    Serial.println("\t\t res: "+ result);
    return stringToBool(result);
}


bool RemoteStateEndpoint::stringToBool(const String &result) const {
    if (result == "net.torbenvoltmer.ffis.common.state.TrueState")
        return true;
    else
        return false;
}


String RemoteStateEndpoint::boolToString(bool value) {
    if (value)
        return "true";
    else
        return "false";
}




