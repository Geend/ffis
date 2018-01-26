#include <ESP8266WiFi.h>
#include <ESP8266mDNS.h>
#include <FS.h>
#include "OTA/OTAManager.h"
#include <WiFiClient.h>
#include <ESP8266HTTPClient.h>
#include <Arduino.h>

#include <TickerScheduler.h>
#include "ArduinoJson.h"
#include "State/State.h"


#include "defines.h"


const char *ssid = HC_SSID;
const char *password = HC_WPA_KEY;



OTAManager otaManager;


#define SWITCH_FLYING 5
#define SWITCH_GRILLING 4

#define LED_FLYING 12
#define LED_GRILLING 13



State flyingState = State(RemoteStateEndpoint("flying"), LED_FLYING);
State grillingState = State(RemoteStateEndpoint("grilling"), LED_GRILLING);


TickerScheduler ts(5);



void refreshLocalState(){
    Serial.println("Refreshing local state");
    flyingState.refreshLocalState();
    grillingState.refreshLocalState();
}

void setup() {
    pinMode(LED_FLYING, OUTPUT);
    pinMode(LED_GRILLING, OUTPUT);

    Serial.begin(115200);
    Serial.println("Booting");

    Serial.print("Init SPIFFS... ");
    SPIFFS.begin();
    Serial.println("done.");


    Serial.print("Connecting to wifi (");
    Serial.print(ssid);
    Serial.print(")... ");

    WiFi.mode(WIFI_STA);
    WiFi.begin(ssid, password);

    while (WiFi.waitForConnectResult() != WL_CONNECTED) {
        Serial.println("Connection Failed! Rebooting...");
        delay(5000);
        ESP.restart();
    }
    Serial.print("done. IP address:");
    Serial.println(WiFi.localIP());

    Serial.print("Starting OTA Manager... ");
    otaManager = OTAManager();
    otaManager.init();
    Serial.println("done.");

    tscallback_t cb = refreshLocalState;
    ts.add(1, 10000, cb, true);

    Serial.println("Setup finished.");

    digitalWrite(LED_GRILLING, 0);
}

void loop() {
    otaManager.handle();
    ts.update();

    if (digitalRead(SWITCH_FLYING) == HIGH) {
        Serial.println("Toogle flying state");
        flyingState.toogleRemoteState();
        delay(2000);
    }

    if (digitalRead(SWITCH_GRILLING) == HIGH) {
        Serial.println("Toogle grilling state");
        grillingState.toogleRemoteState();
        delay(2000);
    }
}


