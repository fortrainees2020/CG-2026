# Ec2 Setup  and connect with Putty

## Step 1: Launch an EC2 Instance
1. Log in to AWS Management Console.
2. Go to EC2 → Instances → Launch instances.
3. Fill in the required details:
4. Name: e.g., MyEC2

5. AMI (Amazon Machine Image): Choose Amazon Linux 2 or Ubuntu (common choices).
6. Instance type: Start with t2.micro (Free Tier eligible).
 Key pair (login):
7.  Create a new key pair (type = RSA, format = .pem).
8.  Download and save it safely (you’ll need it to connect).
Network settings:
9. Allow SSH (port 22).   Also allow HTTP/HTTPS if you plan to run a web server.
10. Storage: Default is fine for testing (8 GB).
11. Click Launch Instance.



## Step 2: Get Your EC2 Public IP
1. Go to EC2 → Instances.
2. Select your instance.
3. Copy the Public IPv4 address.

### Option1 to connect:
1. Using Git Bash / OpenSSH (with .pem)
AWS gives you a .pem key when you create the EC2 instance.
Git Bash (or even PowerShell with OpenSSH) can use .pem directly.
Steps:
chmod 400 mykey.pem
ssh -i mykey.pem ec2-user@<EC2-Public-IP>

** Best when: **
You’re comfortable with a terminal.

### Option 2: 
#### Step 1: Download your EC2 Key Pair (.pem)

When launching your EC2 instance in AWS Console:
Under Key Pair (login) → select Create new key pair.
Choose RSA and file format .pem.
Download the .pem file (example: mykey.pem).
⚠️ Keep it safe! AWS won’t let you download it again.

#### 2. Step 2: Install PuTTY & PuTTYgen
Download from https://www.putty.org
Install it → this gives you PuTTY (for SSH) and PuTTYgen (to convert keys).

#### Step 3: Convert .pem to .ppk using PuTTYgen
Open PuTTYgen.
Click Load.
Change file type from PuTTY Private Key (*.ppk) → select All Files (.).
Browse and select your mykey.pem.

You’ll see “Successfully imported foreign key”.

Click ***Save private key***.

You can choose to set a passphrase for extra protection.

Save as mykey.ppk.
⚠️ Keep .ppk secure, just like .pem.

#### Step 4: Open PuTTY and Configure Session

Open PuTTY.
In Host Name (or IP address) → enter your EC2 details:
ec2-user@<EC2-Public-IP>

***Example: ec2-user@3.92.115.40***
(username is always ec2-user for Amazon Linux AMI).
In the Category pane (left):

Navigate to Connection → SSH → Auth → Credentials.

Under Private key file for authentication, click Browse and select your mykey.ppk.
Save it as mykey.pem.


### Setup A web server

sudo yum update -y
sudo yum install -y httpd

sudo systemctl start httpd
sudo systemctl enable httpd

echo "<h1>Hello from EC2 Web Server </h1>" | sudo tee /var/www/html/index.html


#  Setup Docker  on EC2
echo "<h1>Hello from EC2 Web Server </h1>" | sudo tee /var/www/html/index.html


Install & Fix Docker on Amazon Linux 2023
1. Update your system

sudo dnf update -y

2. Install Docker

sudo dnf install -y docker

3. Start Docker service

sudo systemctl start docker

4. Enable Docker at boot

sudo systemctl enable docker

5. Add ec2-user to Docker group

sudo usermod -aG docker ec2-user

6. Apply group change immediately
Either log out and log back in, OR run:

newgrp docker

7. Verify your user has docker group

groups
👉 You should see: ec2-user ... docker

8. Run Docker test container

docker run hello-world

## Instal Docker compose 
1. sudo curl -SL https://github.com/docker/compose/releases/download/${DOCKER_COMPOSE_VERSION}/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
2. sudo chmod +x /usr/local/bin/docker-compose
3. docker-compose version or docker compose version
4.docker compose up --build
5. curl http://98.88.17.8:9292/api/products


# How to move the file from local system to Ec2 with Secured connection 
1. Go local machine and make you have .pem file say vmkey.pem Eg: Downloads> vmkey.pem.
2. Make sure you about project folder
3. Student@WIN-JD7VQ83KVA7 MINGW64 ~/Downloads
   $ scp -i vmkey.pem -r ./western-union-main/western-union-main/docker/16-aws-employee-producer ec2-user@3.91.161.1:/home/ec2-user/
4. verify the folder in ec2 virtual machine


## Install java 
sudo yum install java-17-amazon-corretto -y

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



sudo yum update -y
sudo amazon-linux-extras install docker -y
sudo service docker start
sudo usermod -aG docker ec2-user.  (logout/login again)(logout/login again)
java -jar myservice.jar
