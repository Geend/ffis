//
// Created by torben on 24.04.16.
//

#ifndef ESP_STATE_H
#define ESP_STATE_H


#include "RemoteStateEndpoint.h"

class State {
private:
    bool value;
    uint8_t stateLed;
    RemoteStateEndpoint remoteStateEndpoint;
public:


    State(RemoteStateEndpoint remoteStateEndpoint, uint8_t stateLed);
    bool setRemoteState(bool value);
    bool getLocalState();
    bool refreshLocalState();
    bool toogleRemoteState();


};


#endif //ESP_STATE_H
