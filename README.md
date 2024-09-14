Problem: gradlew has no permission to run?
Fix: chmod u+x ./gradlew

Problem: docker no permissions
Fix: add current user to group

Problem: docker has no permissions after adding to group
Temporary fix: newgrp docker

