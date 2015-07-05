package com.rest.rutracker.rutrackerrestclient.data.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created by ilia on 23.06.15.
 *
 * @author ilia
 */
public class ApiResponse {
    private InputStream mInputSream;
    private int status;

    public ApiResponse() {
		this(0, null);
    }

    public ApiResponse(int status, InputStream inputStream) {
        this.status = status;
        this.mInputSream = inputStream;
    }

	public InputStreamReader getInputStreamReader() {
        if (mInputSream == null) {
            return null;
        }
        return new InputStreamReader(mInputSream);
    }

    public InputStream getInputSream() {
        return mInputSream;
    }

    public int getStatus() {
        return status;
    }

	public String getAsText(){
		if(mInputSream == null) return  null;

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			br = new BufferedReader(new InputStreamReader(mInputSream, Charset.forName("cp1251")));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return sb.toString();
	}
}
