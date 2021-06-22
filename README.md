[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<br />
<p align="center">

  <h3 align="center">Gomoku Client</h3>

  <p align="center">
    A Sample Application for demoing the <a href="https://github.com/DomhnallP/Gomoku-Server"><strong>Gomoku Server</strong></a> application
    <br />
    <a href="https://github.com/DomhnallP/Gomoku-client"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/DomhnallP/Gomoku-client/issues">Report Bug</a>
    ·
    <a href="https://github.com/DomhnallP/Gomoku-client/issues">Request Feature</a>
  </p>
</p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation and Launching</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#usage">Configuration</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

This project was built to showcase how a multiplayer game can be build on a client-server architecture using HTTP to transfer Player Movement Actions as well as a Synchronised Game State between the clients and the Game Server. This is a demo client application that will allow the user to play a game of gomoku in the command line


### Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [Project Lombok](https://projectlombok.org/)



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Prerequisites

The Gomoku Client application relies on the Gomoku Server for Game logic, to install it please follow the instructions found here: [Gomoku Server](https://github.com/DomhnallP/Gomoku-server)

### Installation and Launching

1. Clone the repo
   ```sh
   git clone https://github.com/DomhnallP/Gomoku-client.git
   ```
2. To run the application
   ```sh
   ./mvnw spring-boot:run
   ```
   _Note: Some terminals do not required the ./ prefix, so try removing it if the project doesn't run_


<!-- USAGE EXAMPLES -->
## Usage

This app connects to the [Gomoku Server](https://github.com/DomhnallP/Gomoku-server) which by default runs on http://localhost:8080, however if the server application is running on a non-default port then te client must be informed of this URL with the property _client.serverAddress_ which is defined in the [application.properties](https://github.com/DomhnallP/Gomoku-Client/blob/master/src/main/resources/application.properties) file

This app runs by default on port 8081 however in order to play a game you will need to launch 2 instances of the app. the port is configurable through the [application.properties](https://github.com/DomhnallP/Gomoku-Client/blob/master/src/main/resources/application.properties) file however if you don't want to copy the repository to two different directories you can also override the properties within the command line.

#### To do this: 

1. Open 2 CMD or Bash terminals
2. Navigate to the project directory in both terminals
3. To run the application in terminal 1, run the following command:
   ```sh
   ./mvnw spring-boot:run
   ```
4. To run the application in terminal 2, run the following command:
   ```sh
   ./mvnw spring-boot:run -Dspring-boot.run.arguments="--server.port=[NEW_PORT] --client.networkAddress=[NEW_PORT]"
   ```
   _Note: Some terminals do not required the ./ prefix, so try removing it if the project doesn't run_



## Configuration

The Allows for a number of properties to be configured within the [Spring Properties File](https://github.com/DomhnallP/Gomoku-client/blob/master/src/main/resources/application.properties). See below a list of available properties: 

| Name                  | Description                                                                                     | Type        |
|-----------------------|-------------------------------------------------------------------------------------------------|-------------|
|      server.port      | sets the localhost port that the game server will use (Default is 8080)                         | port number |
| client.networkAddress | The URL that the client application is running on (Used to sync the game state with the server) |     URL     |
|  client.serverAddress | The URL of the Gomoku Server application to connect to                                          |     URL     |


<!-- ROADMAP -->
## Roadmap

See the [open issues](https://github.com/DomhnallP/Gomoku-client/issues) for a list of proposed features (and known issues).


<!-- CONTACT -->
## Contact

Domhnall ó Póil -  dm.poole@hotmail.com

Project Link: [https://github.com/DomhnallP/Gomoku-client](https://github.com/DomhnallP/Gomoku-client)


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/DomhnallP/Gomoku-client.svg?style=for-the-badge
[contributors-url]: https://github.com/DomhnallP/Gomoku-client/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/DomhnallP/Gomoku-client.svg?style=for-the-badge
[forks-url]: https://github.com/DomhnallP/Gomoku-client/network/members
[stars-shield]: https://img.shields.io/github/stars/DomhnallP/Gomoku-client.svg?style=for-the-badge
[stars-url]: https://github.com/DomhnallP/Gomoku-client/stargazers
[issues-shield]: https://img.shields.io/github/issues/DomhnallP/Gomoku-client.svg?style=for-the-badge
[issues-url]: https://github.com/DomhnallP/Gomoku-client/issues
[license-shield]: https://img.shields.io/github/license/DomhnallP/Gomoku-client.svg?style=for-the-badge
[license-url]: https://github.com/DomhnallP/Gomoku-Client/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/DomhnallP