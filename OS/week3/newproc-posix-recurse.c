/**
 * This program forks a separate process using the fork()/exec() system calls.
 *
 * Figure 3.10
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts  - Seventh Edition
 * Copyright John Wiley & Sons - 2005.
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>

int main()
{
pid_t pid;

	/* fork a child process */
	pid = fork();

	if (pid < 0) { /* error occurred */
		fprintf(stderr, "Fork Failed\n");
		exit(-1);
	}
	else if (pid == 0) { /* child process */
		printf("I am the child %d\n",pid);
		printf("I am the child's real %d\n",getpid());
		
		pid_t pid1;
		
		pid1 = fork();
		if(pid1 == 0)
			execlp("./a.out","a.out",NULL);
		else
		{	
			wait();
			execlp("/bin/ls","ls",NULL);
		}
	}
	else { /* parent process */
		/* parent will wait for the child to complete */
		printf("I am the parent (child pid) %d (parent pid) %d\n",pid,getpid());
		wait(NULL);
		
		printf("Child Complete\n");
		exit(0);
	}
}
