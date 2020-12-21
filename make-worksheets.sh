#!/usr/bin/env bash

script=$0
default_source_dir=src/script/scala
default_target_dir=src/main/scala/worksheet
default_ext="worksheet.sc"


fatal_message() {
cat <<EOF
NOTE: If worksheet evaluation fails, try removing the -Xfatal-warnings flag
in build.sbt.
EOF
}

help() {
  cat <<EOF
Copies $default_source_dir to $default_target_dir, the default location for Metals,
and renames all the files to "*.worksheet.sc", the default extension for
VSCode worksheets.

$(fatal_message)

Usage: $script [options]

Where the options are the following:
-h | --help           Shows this help and quits
-f | --force          Force deletion of old $target_dir location.
                      By default, it won't overwrite an old copy.
-s | --source dir     Read the scripts from "dir" (default: "$default_source_dir").
-t | --target dir     Write results to "dir" (default: "$default_target_dir").
-e | --ext extension  Use ".extension" (default: ".$default_ext").
                      (A leading "." is optional; it will be added if needed.)
EOF
}

error() {
  echo "ERROR: $@"
  echo
  help
  exit 1
}

source_dir=$default_source_dir
target_dir=$default_target_dir
ext1=$default_ext
force_delete=
while [[ $# -gt 0 ]]
do
  case $1 in
    -h|--help)
      help
      exit 0
      ;;
    -f|--f*)
      force_delete=true
      ;;
    -t|--t*)
      shift
      target_dir=$1
      ;;
    -s|--s*)
      shift
      source_dir=$1
      ;;
    -e|--e*)
      shift
      ext1=$1
      ;;
    *)
      error "Unknown argument: $1"
      ;;
  esac
  shift
done

if [[ -d $target_dir ]]
then
  if [[ -n $force_delete ]]
  then
    $NOOP rm -rf $target_dir
  else
    error "Won't overwrite old $target_dir by default."
  fi
fi

ext=$(echo $ext1 | sed -e 's/^\.*//')

if [[ -z $NOOP ]]
then
  cp -r $source_dir $target_dir
  find $target_dir -name '*.scala' | while read f
  do
    ff=${f%.scala}
    $NOOP mv $f $ff.$ext
  done
fi

cat <<EOF

Finished copying $source_dir to $target_dir and changing file extensions
to $ext.

$(fatal_message)

EOF
