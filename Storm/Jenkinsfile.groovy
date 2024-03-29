node {
  stage("Update jenkins"){
      properties([parameters([string(defaultValue: '18.195.31.232', description: 'Please provide IP', name: 'khuslen', trim: true)])])
  }
  stage("Install git"){
    sh "ssh ec2-user@${khuslen} sudo yum install git python python-pip -y"

  }
  stage("Existing Directory"){
    sh "ssh ec2-user@${khuslen} sudo rm -rf /home/ec2-user/stormpath-flask-sample"
  }
  stage("Copy Script"){
    sh "scp script.sh ec2-user@${khuslen}:/home/ec2-user"
    sh "ssh ec2-user@${khuslen} bash script.sh"
  }
  stage("Pull Repo"){
    sh "ssh ec2-user@${khuslen} git clone https://github.com/Khuslentuguldur/stormpath-flask-sample.git"
  }
  stage("Install requirements"){
    // sh "virtualenv /tmp/venv"
    // sh ". /tmp/venv/bin/activate"
    sh"echo Hello"

  }
  stage("Pip install"){
    sh "ssh ec2-user@${khuslen} sudo pip install -r /home/ec2-user/stormpath-flask-sample/requirements.txt"
  }
  stage("Run App"){
    sh "ssh ec2-user@${khuslen} sudo python /home/ec2-user/stormpath-flask-sample/app.py"
  }
}
