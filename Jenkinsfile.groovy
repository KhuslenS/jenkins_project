node {
  stage("Update jenkins"){
      properties([parameters([string(defaultValue: '18.195.31.232', description: 'Please provide IP', name: 'khuslen', trim: true)])])
  }
  stage("Install git"){
    sh "ssh ec2-user@${khuslen} sudo yum install git -y"
    sh "ssh ec2-user@${khuslen} sudo yum install python3 -y"
    sh "ssh ec2-user@${khuslen} sudo yum install python3-pip"

  }
  stage("Existing Directory"){
    sh "ssh ec2-user@${khuslen}" sudo rm -rf /home/ec2-user/flask-examples
  }
  stage("Pull Repo"){
    sh "ssh ec2-user@${khuslen} git clone https://github.com/miguelgrinberg/flask-examples.git"
  }
  stage("Install requirements"){
    // sh "virtualenv /tmp/venv"
    // sh ". /tmp/venv/bin/activate"
    sh"echo Hello"

  }
  stage("Pip install"){
    sh "ssh ec2-user@${khuslen} sudo pip install -r ~/flask-examples/requirements.txt"
  }
  stage("Run App"){
    sh "ssh ec2-user@${khuslen} sudo python ~/flask-examples/01-hello-world/hello.py"
  }
}
