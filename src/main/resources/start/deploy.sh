#!/bin/sh

basePath=$(cd `dirname $0`; pwd)
now=`date +%m%d%H%M`

# war的相对路径
projectWar=${basePath}"/"$1
# 项目的路径
project=${basePath}"/"$2
# 临时移动的项目路径
projectTmp=${basePath}"/"$2"Tmp"
#


bin=$(cd `dirname $0`; pwd)
echo "$now"
echo "$projectWar"
echo "$project"
echo "$projectTmp"

if [ ! -n "${projectWar}" ]; then
    echo "***Usage: $0 [project.war]"
    exit 0
fi
if [ ! -f "${projectWar}" ]; then
    echo "***Error: ${projectWar} does not exist."
    exit 0
fi
if [ ! "${projectWar##*.}" = "war" ]; then
    echo "***Error: ${projectWar} is not a war file."
    exit 0
fi

echo "Deploy ${projectWar##*/}..."

echo "mv -f ${project} ${projectTmp}"

mv -f ${project} ${projectTmp}

unzip -qo ${projectWar} -d ${project}

echo "cp -f ${projectTmp}/WEB-INF/classes/*.properties ${project}/WEB-INF/classes/"

cp -f ${projectTmp}"/WEB-INF/classes/*.properties" ${project}"/WEB-INF/classes/"
cp -f ${projectTmp}"/WEB-INF/classes/*.xml" ${project}"/WEB-INF/classes/"

#sleep 2s

zip -r ${project}${now}".zip" ${projectTmp}


echo "cp -f ${projectTmp}/WEB-INF/classes/*.properties ${project}/WEB-INF/classes/"
echo "cp -f ${projectTmp}/WEB-INF/classes/*.xml ${project}/WEB-INF/classes/"
echo "rm -rf ${projectTmp}"
echo "rm -rf ${projectWar}"
# echo "Restart tomcat..."
# exec ${basePath}/"bin"/startup.sh