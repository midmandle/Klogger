/**
	Modififed by Dr. Andy Nisbet, to have similar functionality (although not exactly the same)
	to the java thread program for ex2 of Operating Systems
 * A pthread program illustrating how to
 * create a simple thread and some of the pthread API
 * This program implements the summation function where
 * the summation operation is run as a separate thread.
 *
 * Most Unix/Linux/OS X users
 * gcc thrd.c -lpthread
 *
 * Solaris users must enter
 * gcc thrd.c -lpthreads
 *
 * Figure 4.6
 *
 * @author Gagne, Galvin, Silberschatz
 * Operating System Concepts  - Seventh Edition
 * Copyright John Wiley & Sons - 2005.
 */

#include <pthread.h>
#include <stdio.h>
#include <malloc.h>
#include <errno.h>

long int sum; /* this data is shared by the thread(s) */
typedef long int Bounds[2];
Bounds *inputs;
void *runner(void *param); /* the thread */

int main(int argc, char *argv[])
{
int i,totalThreads,done,error,terminated;
sum = 0; //initialise sum!!!
pthread_t tid; /* the thread identifier */
pthread_attr_t attr; /* set of attributes for the thread */

if (argc != 2) {
	fprintf(stderr,"usage: a.out <integer value>\n");
	/*exit(1);*/
	return -1;
}

if (atoi(argv[1]) < 0) {
	fprintf(stderr,"Argument %d must be non-negative\n",atoi(argv[1]));
	/*exit(1);*/
	return -1;
}
totalThreads = atoi(argv[1]);
inputs = (Bounds *)malloc(sizeof(Bounds) * totalThreads);
pthread_attr_init(&attr);

for(i = 0 ; i < totalThreads;i++)	{
/* get the default attributes */
	/* create the threads */
                inputs[i][0] = (int)(i * (128.0*totalThreads/totalThreads));
                inputs[i][1] = (int)((i+1) * (128.0*totalThreads/totalThreads));
                if(inputs[i][1] > 128*totalThreads) inputs[i][1] = 128*totalThreads;
                if(i == (totalThreads -1)) inputs[i][1] =128*totalThreads;
		printf("%lu %lu\n",inputs[i][0],inputs[i][1]);
		error = pthread_create(&tid,&attr,runner,(void *) (&(inputs[i])) );
		if(error != 0 ) {
			printf("ERRNO is set  %d\n",errno);
			switch(error) {
				case EAGAIN:
					printf("System lacked necessary resources top create another thread EAGAIN %d\n",error);
				break;
				case EINVAL:
					printf("Value specified by attr is invalid (2nd argument to pthread_create) EINVAL %d\n",error);
				break;
				case EINTR:
					printf("EINTR %d\n",error);
				break;
				case EPERM:
					printf("Caller does not have appropriate permission to set the required scheduling policy EPERM %d\n",error);
				break;
				case ENOMEM:
					printf("Out of memory ENOMEM %d\n",error);
				break;
				
				default:	
					printf("UNKNOWN ERROR %d errno is %d\n",error,errno);
				break;
			}
			return(-error);
		}
		//pthread_join(tid,NULL);
	
}
/* now wait for the thread to exit */
	printf("Termination testing begins\n");

	done =0 ;
	terminated = 0;
	while(!done) {
		done  = 1;
		terminated = 0;
		for(i = 0;i < totalThreads;i++)	 {
			if(inputs[i][1] != -1) done = 0;
			else terminated++;
		}
		printf("%d threads have terminated so far\n",terminated);
		sleep(10);
	}
	for(i = 0; i < totalThreads;i++)
		sum= sum + inputs[i][0];
	printf("sum = %lu\n",sum);
}

/**
 * The thread will begin control in this function
 */
void *runner(void *param) 
{
	int lower , upper;
	long int sum;
	sum = 0;
	lower = ((Bounds *)param)[0][0];
	upper = ((Bounds *)param)[0][1];


	for (; lower < upper; lower++)
		sum += lower;
	((Bounds *)param)[0][0] = sum;
	((Bounds *)param)[0][1] = -1;
		
		
	pthread_exit(0);
}
