package br.com.caelum.vraptor4.ioc.cdi;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.util.AnnotationLiteral;

//TODO unit tests
@SuppressWarnings("serial")
public class ScopeInfo {

	private Class<? extends Annotation> scope;
	private final Map<Class<? extends Annotation>, AnnotationLiteral<? extends Annotation>> scopesLiterals = new HashMap<Class<? extends Annotation>, AnnotationLiteral<? extends Annotation>>();

	{
		scopesLiterals.put(ApplicationScoped.class,new AnnotationLiteral<ApplicationScoped>(){});
		scopesLiterals.put(RequestScoped.class,new AnnotationLiteral<RequestScoped>(){});
	}

	public ScopeInfo(Class<? extends Annotation> scope) {
		super();
		this.scope = scope;
	}

	public ScopeInfo() {
	}

	public void setScope(Class<? extends Annotation> scope) {
		this.scope = scope;
	}

	public boolean hasScope() {
		return scope != null;
	}

	public Class<? extends Annotation> getScope() {
		return scope;
	}

	public AnnotationLiteral<? extends Annotation> getLiteral() {
		if(this.scope==null){
			throw new IllegalStateException("Should not get literal if scope is not defined");
		}
		return this.scopesLiterals.get(this.scope);
	}

}
