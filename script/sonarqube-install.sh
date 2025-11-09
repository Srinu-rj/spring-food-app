#!/bin/bash

#TODO HOW TO START SonarQube USING BASH-SCRIPT
#TODO https://medium.com/@singhragvendra503/sonarqube-9-9-e3c91aff8d10
#TODO mkdir sonar && cd sonar
#TODO sodo vi sonar.sh
#TODO sudo chmod +x sonar.sh
#TODO ./sonar.sh
#TODO sudo systemctl status sonar.service

function check_java_version() {
    if type -p java && [[ "$(java -version 2>&1)" =~ "17\." ]]; then
        return 0
    else
        return 1
    fi
}

if [ "$1" == "--uninstall" ] || [ "$1" == "-u" ]; then
    # Stop and remove the SonarQube service
    sudo systemctl stop sonar.service
    sudo systemctl disable sonar.service
    sudo rm /etc/systemd/system/sonar.service
    sudo systemctl daemon-reload

    # Remove SonarQube installation
    sudo rm -r /opt/sonarqube

    # Remove SonarScanner installation
    sudo rm -r /opt/sonar-scanner



    echo "Uninstallation complete."
    exit 0
fi

if ! check_java_version; then
    echo "Java version 17 not found. Installing..."
    sudo apt update -y
    sudo apt install openjdk-17-jdk unzip -y
fi

sonar_version="9.9.1.69595"
wget "https://binaries.sonarsource.com/Distribution/sonarqube/sonarqube-$sonar_version.zip" -O "sonarqube-$sonar_version.zip"
sudo unzip "sonarqube-$sonar_version.zip" -d /opt/
sudo rm "sonarqube-$sonar_version.zip"
sudo mv "/opt/sonarqube-$sonar_version" /opt/sonarqube
sudo chown $USER:$USER /opt/sonarqube/ -R

sudo tee /etc/systemd/system/sonar.service <<EOF
[Unit]
Description=SonarQube service
After=syslog.target network.target

[Service]
Type=forking

ExecStart=/opt/sonarqube/bin/linux-x86-64/sonar.sh start
ExecStop=/opt/sonarqube/bin/linux-x86-64/sonar.sh stop

User=$USER
Group=$USER
Restart=always

[Install]
WantedBy=multi-user.target
EOF

sudo systemctl daemon-reload
sudo systemctl start sonar.service

scanner_version="4.8.0.2856" # 8.9
wget "https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-$scanner_version-linux.zip" -O "sonar-scanner-cli-$scanner_version.zip"
sudo unzip "sonar-scanner-cli-$scanner_version.zip" -d /opt/
sudo rm "sonar-scanner-cli-$scanner_version.zip"
sudo mv "/opt/sonar-scanner-$scanner_version-linux" /opt/sonar-scanner
sudo chown $USER:$USER /opt/sonar-scanner/ -R

plugin_version="4.1.2"
sudo mkdir -p /opt/sonarqube/extensions/plugins
wget "https://github.com/cnescatlab/sonar-cnes-report/releases/download/$plugin_version/sonar-cnes-report-$plugin_version.jar" -P /opt/sonarqube/extensions/plugins
sudo systemctl restart sonar.service
