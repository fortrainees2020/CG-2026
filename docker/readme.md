# Docker Commands and Steps

## Basic Docker Commands
```bash
docker version
docker pull ubuntu
docker images
docker run -it -d ubuntu
docker container ls -a
docker container stop
docker container prune
docker exec -it 00220e3ed7c1 bash
exit
docker login
docker rmi 356
docker rmi -f 123
docker image build -t <name of image> .
```
## Employee Service
```
D:\SourceCode-1-Jul-2020\TestAWSPracticeCheck\aws-employee>docker image build -t ij005-emp .
D:\SourceCode-1-Jul-2020\TestAWSPracticeCheck\aws-employee>docker image tag ij005-emp ashujauhari/ij005-emp
D:\SourceCode-1-Jul-2020\TestAWSPracticeCheck\aws-employee>docker image push ashujauhari/ij005-emp
D:\SourceCode-1-Jul-2020\TestAWSPracticeCheck\aws-employee>docker pull ashujauhari/ij005-emp
D:\SourceCode-1-Jul-2020\TestAWSPracticeCheck\aws-employee>docker container run -d -p 7000:7000 --network emp-dept-net --name emp-service ashujauhari/ij005-emp
```
## Test:
http://localhost:7000/api/emp/employees


## Department Service (Feign)
```
D:\SourceCode-1-Jul-2020\TestAWSPracticeCheck\aws-dept-feign>docker image build -t ij005-dept .
D:\SourceCode-1-Jul-2020\TestAWSPracticeCheck\aws-dept-feign>docker image tag ij005-dept ashujauhari/ij005-dept
D:\SourceCode-1-Jul-2020\TestAWSPracticeCheck\aws-dept-feign>docker image push ashujauhari/ij005-dept
D:\SourceCode-1-Jul-2020\TestAWSPracticeCheck\aws-dept-feign>docker pull ashujauhari/ij005-dept
D:\SourceCode-1-Jul-2020\TestAWSPracticeCheck\aws-dept-feign>docker run -d -p 7100:7100 --network emp-dept-net --env EMPLOYEE_SERVICE=http://emp-service:7000 --name dept-service ashujauhari/ij005-dept
```
## Test :
http://localhost:7100/api/dept/allemp
