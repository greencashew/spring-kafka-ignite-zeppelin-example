#!/bin/bash

export $(grep -v '^#' ../.env | xargs) &>/dev/null

ZEPPELIN_VERSION="0.9.0"
ZEPPELIN_FILE="zeppelin-$ZEPPELIN_VERSION-bin-all"

cd ../
INSTALL_DIR=$(pwd)

[[ -d "vendor" ]] || mkdir vendor
cd vendor || exit

if [[ -z "${ZEPPELIN_HOME}" ]]; then
  wget https://apache.mirrors.tworzy.net/zeppelin/zeppelin-$ZEPPELIN_VERSION/$ZEPPELIN_FILE.tgz
  tar xvf $ZEPPELIN_FILE.tgz
  echo "ZEPPELIN_HOME=$INSTALL_DIR/vendor/$ZEPPELIN_FILE" >>"$INSTALL_DIR/.env"
  echo "ZEPPELIN installed \$ZEPPELIN_HOME saved to .env"
  rm -f $ZEPPELIN_FILE.tgz
else
  echo "Skipping installation of Zeppelin. ZEPPELIN_HOME already set up: $ZEPPELIN_HOME"
fi
