package org.reflection.service.etc;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface ExcelFileUploadService {

    public void upload(InputStream inputStream);

    public List<Map<String, Object>> getData(InputStream inputStream);
}
