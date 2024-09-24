# fetch-rewards-homework

## Description
https://github.com/fetch-rewards/receipt-processor-challenge

## Prerequisites
Make sure you have Docker installed on your system. You can download it from [Docker's official website](https://www.docker.com/get-started).


## Building the Docker Image
To build the Docker image from this project root, run the following command in your terminal:


```bash
$ docker build --no-cache -t app .
```

## Running the Docker Container
After building the image, you can run the container with the following command:

```bash
$ docker run -p 8080:8080 app
```

The web server will be accessible at  `http://localhost:8080/`
