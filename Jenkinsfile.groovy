node {
  stage("Pull Repo"){
    properties([parameters([string(defaultValue: '18.195.31.232', description: 'Please provide IP', name: 'Environment', trim: true)])])
    git "https://github.com/miguelgrinberg/flask-examples.git"
  }
  stage("Install requirements"){
    sh "virtualenv /tmp/venv"
    sh ". /tmp/venv/bin/activate"
  }
  stage("Pip install"){
    sh "pip install -r requirements.txt"
  }
  stage("Run App"){
    sh " python /tmp/venv/01-hello-world/hello.py"
  }
}
