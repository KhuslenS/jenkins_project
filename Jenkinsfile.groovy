node {
  stage("Update jenkins"){
      properties([parameters([string(defaultValue: '18.195.31.232', description: 'Please provide IP', name: 'Env', trim: true)])])
  }
  stage("Install git"){
    sh "ssh ec2-user@${Env} sudo yum install git -y"
    sh "ssh ec2-user@${Env} sudo yum install python3 -y"
    sh "ssh ec2-user@${Env} sudo yum install python3-pip"

  }
  stage("Pull Repo"){
    sh "ssh ec2-user@${Env} git clone https://github.com/miguelgrinberg/flask-examples.git"
  }
  stage("Install requirements"){
    // sh "virtualenv /tmp/venv"
    // sh ". /tmp/venv/bin/activate"
    sh"echo Hello"

  }
  stage("Pip install"){
    sh "ssh ec2-user@${Env} sudo pip install -r ~/flask-examples/requirements.txt"
  }
  stage("Run App"){
    sh "ssh ec2-user@${Env} sudo python ~/flask-examples/01-hello-world/hello.py"
  }
}
