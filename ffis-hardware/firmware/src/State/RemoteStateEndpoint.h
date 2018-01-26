//
// Created by torben on 24.04.16.
//

#ifndef ESP_REMOTESTATEENDPOINT_H
#define ESP_REMOTESTATEENDPOINT_H



class RemoteStateEndpoint {

public:
    RemoteStateEndpoint(String url);
    bool setState(bool state);
    bool getState();


private:
    String host;
    String url;
    String sendGetRequest(String url);
    bool extractStateFromJson(String json);
    String boolToString(bool value);


    bool stringToBool(const String &result) const;
};


#endif //ESP_REMOTESTATEENDPOINT_H
