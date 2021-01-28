#!/bin/bash

export $(grep -v '^#' ../.env | xargs) &>/dev/null

IGNITE_VERSION=2.9.1
IGNITE_FILE=apache-ignite-$IGNITE_VERSION-bin

cd ../
INSTALL_DIR=$(pwd)

[[ -d "vendor" ]] || mkdir vendor
cd vendor || exit

if [[ -z "${IGNITE_HOME}" ]]; then
  wget https://ftp.ps.pl/pub/apache//ignite/$IGNITE_VERSION/$IGNITE_FILE.zip
  unzip $IGNITE_FILE.zip || exit
  echo "IGNITE_HOME=$INSTALL_DIR/vendor/$IGNITE_FILE" >>"$INSTALL_DIR/.env"
  rm -f $IGNITE_FILE.zip
  echo "IGNITE installed \$IGNITE_HOME saved to .env"
else
  echo "Skipping installation of Ignite. IGNITE_HOME already set up: $IGNITE_HOME"
fi
