package client;

import service.Service;
import service2.Service2;

public class Client {
  public static void main(String[] args) {
    Service server = new Service();
    server.service();

    Service2 server2 = new Service2();
    server2.service();
  }
}
