#!/bin/bash

export $(grep -v '^#' ../.env | xargs) &>/dev/null

if [ -z "$ZEPPELIN_HOME" ]; then
  echo "Please set \$ZEPPELIN_HOME env. At .env. If kafka missed use install-zeppelin.sh"
  exit
fi

"$ZEPPELIN_HOME"/bin/zeppelin-daemon.sh start
