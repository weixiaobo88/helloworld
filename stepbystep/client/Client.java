package client;

import service.Service;

public class Client {
  public static void main(String[] args) {
    Service server = new Service();
    server.service();
  }
}
