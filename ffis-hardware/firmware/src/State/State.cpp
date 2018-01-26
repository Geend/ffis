//
// Created by torben on 24.04.16.
//

#include <Arduino.h>
#include "State.h"



State::State(RemoteStateEndpoint remoteStateEndpoint, uint8_t stateLed):remoteStateEndpoint(remoteStateEndpoint), stateLed(stateLed){
    this->stateLed = stateLed;
}

bool State::setRemoteState(bool value) {
    digitalWrite(this->stateLed, value);
    this->remoteStateEndpoint.setState(value);
    digitalWrite(this->stateLed, value);
}

bool State::getLocalState() {
    return this->value;
}

bool State::refreshLocalState() {
    Serial.println("rls");
    bool result = this->remoteStateEndpoint.getState();
    this->value = result;
    Serial.println("changen led " + this->stateLed);
    digitalWrite(this->stateLed, value);
    return result;
}

bool State::toogleRemoteState() {
    this->value  = !this->value;
    return setRemoteState(this->value);
}





