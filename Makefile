LATEST_TAG?=`git tag|tail -1`

info:
	echo "Usage: make clean|build|release|publish"

clean:
	gradle clean

build:
	gradle build

release:
	gradle release

publish:
	git checkout tags/${LATEST_TAG}
	gradle build install uploadArchives
	git checkout master
