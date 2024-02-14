# ADAPTED FROM OUR CODE IN LECTURE
``
CPATH='.:../lib/hamcrest-core-1.3.jar:../lib/junit-4.13.2.jar'

rm -rf student
rm -rf grading

mkdir grading

git clone $1 student 2> git-output.txt
if [[ $? -ne 0 ]]
then
    cat git-output.txt
    echo "Git project failed to clone, see error above."
    exit
fi
echo 'Finished cloning.'

if [[ -f student/ListExamples.java ]]
then 
    cp student/ListExamples.java grading/
    cp TestListExamples.java grading/
else
    echo "Where's your ListExamples.java file? Is it misnamed, or hidden in a directory?"
    exit
fi

cd grading

javac -cp $CPATH *.java
if [[ $? -ne 0 ]]
then
    echo "The program failed to compile, see error above."
    exit
fi
java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > output.txt

lastline=$(cat output.txt | tail -n 2 | head -n 1)
if [[ $(echo $lastline|cut -d ' ' -f 1)  == 'OK' ]]
then
    tests=$(echo $lastline | cut -d '(' -f 2 | cut -w -f 1)
    echo "Your score is $tests/$tests. Yay!"
else
    tests=$(echo $lastline | cut -d ' ' -f 3 | cut -d ',' -f 1)
    failures=$(echo $lastline | cut -d ' ' -f 5)
    successes=$((tests - failures))
    echo "Your score is $successes/$tests."
fi


# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests
