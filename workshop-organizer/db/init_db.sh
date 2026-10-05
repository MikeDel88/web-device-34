#!/bin/bash

require_global_var() {

    [[ -z "$2" ]] && echo "Variable '$1' is empty, can't initialize database" && return 1

    return 0
}

process_folder() {

    for file in $(find "$1" -name "*.sql" | sort)
    do
        echo "Processing file <$file>..."
        psql -h "$2" -U "$3" -d "$4" -a -f ${file} || return $?
    done

    return 0
}



SCRIPTS_FOLDER=$1

require_global_var "DATABASE_URL" "${DATABASE_URL}" || exit $?


if [[ ${DATABASE_URL} =~ ^postgres:\/\/(.+?):(.+?)@(.+?):([0-9]+)\/(.+?)$ ]]; then

    USER=${BASH_REMATCH[1]}
    PASSWORD=${BASH_REMATCH[2]}
    HOST=${BASH_REMATCH[3]}
    PORT=${BASH_REMATCH[4]}
    DB=${BASH_REMATCH[5]}

    export PGPASSWORD=${PASSWORD}

    echo "Initialize database '${DB}' on '${HOST}' with user '${USER}'"

    process_folder "${SCRIPTS_FOLDER}" "${HOST}" "${USER}" "${DB}"

    rc=$?

    (( $rc == 0 )) && echo "Initialization successful" || echo "Initialization failed"

    exit $rc
fi

