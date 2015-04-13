package my.sandbox.maxfolder.monitor;

import java.nio.file.Path;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FolderUtilsMonitor {

	final Logger logger = LoggerFactory.getLogger(FolderUtilsMonitor.class);

	@Around("execution(* my.sandbox.maxfolder.util.FolderUtils.createFolder(..))")
	public Object logItCreate(ProceedingJoinPoint pjp) throws Throwable {

		// get argument - the path to create a folder
		Object[] args = pjp.getArgs();
		Path folder = (Path) args[0];

		// start stopwatch
		long before = System.currentTimeMillis();

		Object retVal = pjp.proceed();

		// stop stopwatch
		long after = System.currentTimeMillis();
		logger.debug("path {}, time {}", folder.toAbsolutePath().toString(), (after - before));

		return retVal;
	}

	@Around("execution(* my.sandbox.maxfolder.util.FolderUtils.copyFileToFolder(..))")
	public Object logItCopy(ProceedingJoinPoint pjp) throws Throwable {

		// get argument - the path to create a folder
		Object[] args = pjp.getArgs();
		Path folder = (Path) args[1];

		// start stopwatch
		long before = System.currentTimeMillis();

		Object retVal = pjp.proceed();

		// stop stopwatch
		long after = System.currentTimeMillis();
		logger.debug("copy to {}, time {}", folder.toAbsolutePath().toString(), (after - before));

		return retVal;
	}

}
