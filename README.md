# Amazing Gaming Setup

## Intellij IDEA
* Checkout the code from repository

```
git clone git@github.com:MafeSanchezCardona/amazing_gaming_wallet.git
```
* Open project and start application. Make sure the main class is AmazingGamingWalletApplication

## Docker
* Pull docker images
```
docker pull mafesanchezcardona/amazing-gaming-wallet:amazing-gaming-wallet
```
* Run docker images. Use the following command and when you finish starting the application you will be able to use the REST APIs
```
docker run --rm -it -p 3000:3000 --net=host -v $(pwd):/usr/src/app --name amazing-gaming-wallet mafesanchezcardona/amazing-gaming-wallet:amazing-gaming-wallet
```
