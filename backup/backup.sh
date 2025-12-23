#!/bin/bash

# 1. Remove folder from ignore
sed -i '/backup/d' .helmignore

# 2. Package Helm chart with backup data
helm package .

# 3. Add the folder back to ignore
echo "backup/" >> .helmignore

echo "Backup package created!"
