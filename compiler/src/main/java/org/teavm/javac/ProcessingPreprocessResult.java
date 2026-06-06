/*
 *  Copyright 2026 Alexey Andreev.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.teavm.javac;

import org.teavm.jso.JSExport;
import org.teavm.jso.JSProperty;

public final class ProcessingPreprocessResult {
    private final boolean ok;
    private final String sourceFileName;
    private final String className;
    private final String javaSource;
    private final String programType;
    private final int lineOffset;
    private final String sketchWidth;
    private final String sketchHeight;
    private final String sketchRenderer;
    private final String tabsJson;
    private final String editsJson;
    private final String issuesJson;

    ProcessingPreprocessResult(boolean ok, String sourceFileName, String className, String javaSource,
            String programType, int lineOffset, String sketchWidth, String sketchHeight, String sketchRenderer,
            String tabsJson, String editsJson, String issuesJson) {
        this.ok = ok;
        this.sourceFileName = sourceFileName;
        this.className = className;
        this.javaSource = javaSource;
        this.programType = programType;
        this.lineOffset = lineOffset;
        this.sketchWidth = sketchWidth;
        this.sketchHeight = sketchHeight;
        this.sketchRenderer = sketchRenderer;
        this.tabsJson = tabsJson;
        this.editsJson = editsJson;
        this.issuesJson = issuesJson;
    }

    @JSExport
    @JSProperty
    public boolean isOk() {
        return ok;
    }

    @JSExport
    @JSProperty
    public String getSourceFileName() {
        return sourceFileName;
    }

    @JSExport
    @JSProperty
    public String getClassName() {
        return className;
    }

    @JSExport
    @JSProperty
    public String getJavaSource() {
        return javaSource;
    }

    @JSExport
    @JSProperty
    public String getProgramType() {
        return programType;
    }

    @JSExport
    @JSProperty
    public int getLineOffset() {
        return lineOffset;
    }

    @JSExport
    @JSProperty
    public String getSketchWidth() {
        return sketchWidth;
    }

    @JSExport
    @JSProperty
    public String getSketchHeight() {
        return sketchHeight;
    }

    @JSExport
    @JSProperty
    public String getSketchRenderer() {
        return sketchRenderer;
    }

    @JSExport
    @JSProperty
    public String getTabsJson() {
        return tabsJson;
    }

    @JSExport
    @JSProperty
    public String getEditsJson() {
        return editsJson;
    }

    @JSExport
    @JSProperty
    public String getIssuesJson() {
        return issuesJson;
    }
}
