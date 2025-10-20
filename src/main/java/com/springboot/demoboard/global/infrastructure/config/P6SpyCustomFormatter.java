package com.springboot.demoboard.global.infrastructure.config;


import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6SpyCustomFormatter implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category,
        String prepared, String sql, String url) {

        if (sql == null || sql.trim().isEmpty()) {
            return "";
        }

        String callerTrace = getCallerTrace();

        return String.format(
            "\n📍 [%s]\n⏱ %d ms | %s\n🧩 SQL:\n%s;\n",
            callerTrace, elapsed, category, sql.trim()
        );


    }

    private String getCallerTrace() {
        StackTraceElement[] stack = new Throwable().getStackTrace();
        String controller = null, service = null, repository = null;

        for (StackTraceElement el : stack) {
            String name = el.getClassName();

            if (name.contains("controller") || name.contains("Controller")) {
                controller = String.format("%s.%s",
                    el.getClassName(), el.getMethodName());
            }

            if (name.contains("service") || name.contains("Service")) {
                service = String.format("%s.%s",
                    el.getClassName(), el.getMethodName());
            }

            if (name.contains("repository") || name.contains("Repository")) {
                repository = String.format("%s.%s",
                    el.getClassName(), el.getMethodName());
            }
        }

        StringBuilder trace = new StringBuilder();
        if (controller != null)
            trace.append("Controller ➜ ");
        if (service != null)
            trace.append("Service ➜ ");
        if (repository != null)
            trace.append("Repository ➜ ");

        if (repository != null)
            trace.append(repository);
        else if (service != null)
            trace.append(service);
        else if (controller != null)
            trace.append(controller);
        else
            trace.append("Unknown Source");

        return trace.toString();
    }
}
