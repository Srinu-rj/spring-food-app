#!/bin/bash

echo "Install k3s cluster on Ubuntu...."
sudo apt update
curl -sfL https://get.k3s.io | sh -
systemctl status k3s

alias k=kubectl
k get nodes
