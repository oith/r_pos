package org.reflection.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.stereotype.Component;

@Component
public class SiteMeshFilter extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/views/layouts/layout.jsp")
//                .addDecoratorPath("/*", "/views/layouts/layout.jsp")
//                .addExcludedPath("/")
                .addExcludedPath("/index")
                .addExcludedPath("/resources/*")
                .addExcludedPath("/webjars/*")
                .addExcludedPath("/login")
                .setMimeTypes("text/html", "application/vnd.wap.xhtml+xml", "application/xhtml+xml");
    }
}


//<sitemesh>
//<mapping path="/login" exclue="true"/>
//<mapping path="/" exclue="true"/>
//<mapping path="/index" exclue="true"/>
//
//<mapping path="/*" decorator="/views/layouts/layout.jsp"/>
//
//<mime-type></mime-type>
//<mime-type>application/vnd.wap.xhtml+xml</mime-type>
//<mime-type>application/xhtml+xml</mime-type>
//</sitemesh>
