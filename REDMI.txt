Aplicatie este distribuita pe 3 github repos:
username:18alexandrusandu
DS2022_Andercou_Alexandru_Assignment2_Backend
DS2022_Andercou_Alexandru_Assignment2_Frontend
DS2022_Andercou_Alexandru_Assignment2
Fiecare repo contine un Dockerfile si un docker-compose.yml file.
In DS2022_Andercou_Alexandru_Assignment2 
Repo-ul DS2022_Andercou_Alexandru_Assignment2 contine 2 foldere: 
SensorSimulatorSD,SensorSimulatorSD2 si MeasurementConsumer.

folderul MeasurementConsumer contine un docerfile si un dockercompose deoarece aplicatia de consumer este deployed pe docker iar SensorSimulator este o aplicatie locala.
SensorSimulator se ruleaza din intelij ,deviceu-iul este configurat din fisierul application propeties.

imaginile de rabbitmq si postgres sunt si ele deployed pe docker.
Pentru docker este nevoie de 2 comenzi pentru fiecare componenta.

In repo: DS2022_Andercou_Alexandru_Assignment2_Backend
1. docker build -t spring_server4 .
1. docker-compose up -d


In repo: DS2022_Andercou_Alexandru_Assignment2_Frontend
3. docker build -t angular_client5 .
4. docker-compose up -d


In repo: DS2022_Andercou_Alexandru_Assignment2 
folder:MeasurementConsumer
5. docker build -t consumer_client .
6. docker-compose up -d






Deployment pe Azure in docker.
Agent :pool local , OS:  windows, name:,agent8
Resource group: AndercouAlexandru30241
Containerregistry:ds2022energyfacilityandercoualexandru
Organization:AndercouAlexandru30241
Project: EnergyUtilityPlatform

