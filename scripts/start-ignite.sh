#!/bin/bash

export $(grep -v '^#' ../.env | xargs) &>/dev/null

if [ -z "$IGNITE_HOME" ]; then
  echo "Please set \$IGNITE_HOME env. At .env. If IGNITE missed use install-ignite.sh"
  exit
fi

"$IGNITE_HOME"/bin/ignite.sh
