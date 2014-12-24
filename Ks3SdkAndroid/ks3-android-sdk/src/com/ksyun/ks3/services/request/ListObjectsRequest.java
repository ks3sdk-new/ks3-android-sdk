package com.ksyun.ks3.services.request;

import com.ksyun.ks3.model.HttpHeaders;
import com.ksyun.ks3.model.HttpMethod;
import com.ksyun.ks3.util.StringUtils;

public class ListObjectsRequest extends Ks3HttpRequest {

	private String prefix;

	private String marker;

	private String delimiter;

	private Integer maxKeys;

	private String encodingType;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public Integer getMaxKeys() {
		return maxKeys;
	}

	public void setMaxKeys(Integer maxKeys) {
		this.maxKeys = maxKeys;
	}

	public ListObjectsRequest(String bucketName) {
		this(bucketName, null, null, null, null);
	}

	public ListObjectsRequest(String bucketName, String prefix) {
		this(bucketName, prefix, null, null, null);
	}

	public ListObjectsRequest(String bucketName, String prefix, String marker,
			String delimiter, Integer maxKeys) {
		setBucketname(bucketName);
		this.prefix = prefix;
		this.marker = marker;
		this.delimiter = delimiter;
		this.maxKeys = maxKeys;
	}

	@Override
	protected void setupRequest() {
		this.setHttpMethod(HttpMethod.GET);
		this.addParams("prefix", prefix);
		this.addParams("marker", marker);
		this.addParams("delimiter", delimiter);
		if (maxKeys != null)
			this.addParams("max-keys", maxKeys.toString());
		if (!StringUtils.isBlank(this.encodingType))
			this.addParams("encoding-type", this.encodingType);
		this.addHeader(HttpHeaders.ContentType, "text/plain");
	}

	@Override
	protected void validateParams() throws IllegalArgumentException {
		if (StringUtils.isBlank(super.getBucketname()))
			throw new IllegalArgumentException(
					"param bucketName can not be blank");
		if (this.maxKeys != null && (this.maxKeys > 1000 || this.maxKeys < 1))
			throw new IllegalArgumentException(
					"maxKeys should between 1 and 1000");
	}

	public String getEncodingType() {
		return encodingType;
	}

	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}

}
