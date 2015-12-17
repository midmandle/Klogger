#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

int main(int agrs, char *argv[])
{
	FILE *procFile;
	int err = 0;
	int iteration = 0;
	char line[255];
	

	procFile = fopen("/proc/cpuinfo", "r");
	if(procFile == NULL)
	{
		err = errno;
		fprintf(stderr, "Error opening file path: %s\n", strerror(err));		
	}

	while(fgets(line, 255, procFile) != NULL)
	{
		iteration++;
		puts(line);
		if(iteration == 10)
			break;
	}

	
	return 0;
}
