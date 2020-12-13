#!/usr/bin/env bash

script=$0
default_target_dir=src/worksheet
default_ext="worksheet.sc"

help() {
  cat <<EOF
Copies src/script to src/worksheets and renames all the files to "*.worksheet.sc",
the default extension for VSCode worksheets.

usage: $script [-h|--help] [-t|--target dir] [-e|--ext extension]
where:
-h | --help           Shows this help and quits
-f | --force          Force deletion of old $target_dir location.
                      By default, it won't overwrite an old copy.
-t | --target dir     Write results to "dir" (default: "$default_target_dir").
-e | --ext extension  Use ".extension" (default: ".$default_ext").
                      (If you prepend a ".", it will be handled correctly.)
EOF
}

error() {
  echo "ERROR: $@"
  echo
  help
  exit 1
}

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

cp -r src/script $target_dir
find $target_dir -name '*.scala' | while read f
do
  ff=${f%.scala}
  $NOOP mv $f $ff.$ext
done
